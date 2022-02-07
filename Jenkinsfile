//github的凭证
def git_auth = "faaf8dd2-ff5c-4588-aca5-b0cd56df51de"
//github仓库地址
def git_url = "git@github.com:endeavor66/dp_test.git"

node {
    stage('拉取源代码') {
        checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
    }
}