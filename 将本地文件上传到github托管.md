# 将本地文件上传到github托管

1.在github创建一个新账号

2.移动到要上传的文件夹

```
cd /path/to/your-folder
```

3.如果文件夹还未初始化为 Git 仓库，运行以下命令：

```
git init
```

4.将本地文件夹关联到 GitHub 仓库：

```
git remote add origin https://github.com/<你的用户名>/<仓库名称>.git
```

5.首先，确认是否正确添加了远程仓库，可以运行以下命令查看远程仓库配置：

```
git remote -v
```

6.将文件夹中的所有文件或者单独文件添加到 Git：

```
git add .
git add foldername/filename
```

7.提交文件到本地 Git 仓库：

```
git commit -m "Initial commit"
```

8.将文件推送到 GitHub 仓库的 `main` 分支：

```
git branch -M main
git push -u origin main或者
git push -u origin master 自动生成一个本地master分支,上传的内容也会新创一个分支存放，得到这个分支上查看
```

9.成功上传示例

```
$ git push -u origin master
Enumerating objects: 3329, done.
Counting objects: 100% (3329/3329), done.
Delta compression using up to 12 threads
Compressing objects: 100% (1911/1911), done.
Writing objects: 100% (3329/3329), 20.98 MiB | 21.81 MiB/s, done.
Total 3329 (delta 1188), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (1188/1188), done.
remote:
remote: Create a pull request for 'master' on GitHub by visiting:
remote:      https://github.com/C-limited/Android_move/pull/new/master
remote:
To https://github.com/****/Android_move.git
 * [new branch]      master -> master
branch 'master' set up to track 'origin/master'.

```

