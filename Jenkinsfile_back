//gitlab的凭证
def git_auth = "faaf8dd2-ff5c-4588-aca5-b0cd56df51de"
//构建版本的名称
def tag = "latest"
//Harbor私服地址
def harbor_url = "172.19.240.221:85"
//Harbor的项目名称
def harbor_project_name = "tensquare"
//Harbor的凭证
def harbor_auth = "5a3152e2-684f-41d1-9022-5290bdd5b516"

node {
    stage('拉取源代码') {
        checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: 'git@github.com:endeavor66/tensquare_back.git']]])
    }
    stage('代码审查') {
        def scannerHome = tool 'sonarqube-scanner'
        withSonarQubeEnv('sonarqube') {
            sh """
            cd ${project_name}
            ${scannerHome}/bin/sonar-scanner
            """
        }
    }
    stage('编译，构建镜像') {
        //定义镜像名称
        def imageName = "${project_name}:${tag}"
        //编译，安装公共工程
        sh "mvn -f tensquare_common clean install"
        //编译，构建本地镜像
        sh "mvn -f ${project_name} clean package dockerfile:build"
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
                execCommand: "/opt/jenkins_shell/deploy.sh $harbor_url $harbor_project_name $project_name $tag $port",
                execTimeout: 120000, flatten: false, makeEmptyDirs: false,
                noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '',
                remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')],
            usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])

    }
}