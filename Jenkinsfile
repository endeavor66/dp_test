//github的凭证
def git_auth = "faaf8dd2-ff5c-4588-aca5-b0cd56df51de"
//github仓库地址
def git_url = "git@github.com:endeavor66/dp_test.git"
//构建版本的名称
def tag = "latest"
//Harbor私服地址
def harbor_url = "172.19.240.221:85"
//Harbor的项目名称
def harbor_project_name = "dp_test"
//Harbor的凭证
def harbor_auth = "5a3152e2-684f-41d1-9022-5290bdd5b516"

node {
    // 接受选择的微服务，切分成数组
    def selectedProjects = "${project_name}".split(",")

    stage('拉取源代码') {
        checkout([$class: 'GitSCM', branches: [[name: "*/master"]], extensions: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
    }
    stage('代码审查') {
        def scannerHome = tool 'sonarqube-scanner'
        withSonarQubeEnv('sonarqube') {
            // 遍历微服务数组，依次执行sonarqube
            for(int i = 0; i < selectedProjects.size(); i++){
                // selectedProjects[i]的格式为 "微服务名@端口"
                def currentProjectName = selectedProjects[i].split("@")[0];
                def currentProjectPort = selectedProjects[i].split("@")[1];

                // 执行sonarqube
                sh """
                    cd ${currentProjectName}
                    ${scannerHome}/bin/sonar-scanner
                """
            }
        }
    }
    stage('编译，构建镜像') {
        //编译，安装公共工程
        sh "mvn -f tensquare_common clean install"

        // 遍历微服务数组，依次构建镜像
        for(int i = 0; i < selectedProjects.size(); i++){
            // selectedProjects[i]的格式为 "微服务名@端口"
            def currentProjectName = selectedProjects[i].split("@")[0];
            def currentProjectPort = selectedProjects[i].split("@")[1];

            //定义镜像名称
            def imageName = "${currentProjectName}:${tag}"

            //编译，构建本地镜像
            sh "mvn -f ${currentProjectName} clean package dockerfile:build"
            //给镜像打标签
            sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"
            //登录Harbor，并上传镜像
            withCredentials([usernamePassword(credentialsId: "${harbor_auth}",
                passwordVariable: 'password', usernameVariable: 'username')]) {
                //登录
                sh "docker login -u ${username} -p ${password} ${harbor_url}"
                //上传镜像
                sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
            }
            //删除本地镜像
            sh "docker rmi -f ${imageName}"
            sh "docker rmi -f ${harbor_url}/${harbor_project_name}/${imageName}"

            //=====以下为远程调用进行项目部署========
            sshPublisher(publishers: [sshPublisherDesc(configName: 'master_server',
                transfers: [sshTransfer(cleanRemote: false, excludes: '',
                    execCommand: "/opt/jenkins_shell/deploy.sh $harbor_url $harbor_project_name $currentProjectName $tag $currentProjectPort",
                    execTimeout: 120000, flatten: false, makeEmptyDirs: false,
                    noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '',
                    remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')],
                usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
        }


    }
}
