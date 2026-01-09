package com.wd.maven.aggregation;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class QuickstartAggregationAction extends AnAction {

    // 使用构造函数设置图标和文本
    public QuickstartAggregationAction() {
        super("Create Maven Aggregation Project",
                "Creates a new Maven multi-module project template",
                IconLoader.getIcon("/icons/create_maven_aggregation.png", QuickstartAggregationAction.class));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        // 创建对话框
        AggregationProjectDialog dialog = new AggregationProjectDialog(project);
        if (dialog.showAndGet()) {
            // 用户点击了确定
            String groupId = dialog.getGroupId();
            String artifactId = dialog.getArtifactId();
            // 生成项目
            AggregationProjectGenerator generator = new AggregationProjectGenerator();
          generator.generateProject(project, groupId, artifactId);

            // 刷新项目视图
            refreshProjectView(project);
          formatAllProjectFiles(project);
        }
    }

  public void formatAllProjectFiles(Project project) {
    List<PsiFile> filesToFormat = new ArrayList<>();
    PsiManager psiManager = PsiManager.getInstance(project);
    ProjectFileIndex fileIndex = ProjectFileIndex.getInstance(project);

    // 1. 遍历项目中的所有文件 (需要在 ReadAction 中进行)
    ReadAction.run(() -> {
      fileIndex.iterateContent(virtualFile -> {
        // 过滤逻辑：
        // 1. 必须是可写的
        // 2. 排除目录
        // 3. (可选) 只处理特定后缀，如 .java, .xml
        if (virtualFile.getFileType() == JavaFileType.INSTANCE
            && !virtualFile.isDirectory()
            && virtualFile.isWritable()) {
          PsiFile psiFile = psiManager.findFile(virtualFile);
          if (psiFile != null) {
            filesToFormat.add(psiFile);
          }
        }
        return true; // 继续遍历
      });
    });

    // 2. 如果文件太多，建议提示用户，或者分批处理
    if (!filesToFormat.isEmpty()) {
      PsiFile[] filesArray = filesToFormat.toArray(new PsiFile[0]);
      // 使用支持多文件的构造函数
      ReformatCodeProcessor processor = new ReformatCodeProcessor(project, filesArray, null, false);
      // 运行处理
      processor.run();
    }
  }

    private void refreshProjectView(Project project) {
        // 使用更高效的刷新方式，避免全量刷新
        VirtualFile baseDir = ProjectUtil.guessProjectDir(project);
        if (baseDir != null) {
            // 只刷新根目录，让IDEA自动检测Maven项目
            baseDir.refresh(false, false);

          // 延迟触发项目结构刷新，让IDEA有时间检测到新的Maven项目
            com.intellij.openapi.application.ApplicationManager.getApplication()
                .invokeLater(() -> {
                    // 触发项目结构刷新
                    baseDir.refresh(false, true);
                });
        }
    }
}
