// Jenkinsfile (Declarative pipeline)
pipeline{
    agent any
    parameters{
        // 输入发布docker images镜像标签
        string(defaultValue: 'v1.0', name: 'image_tag', description: '输入发布docker images镜像标签')
        // 选择性参数可以定义构建的环境
        choice(name: 'projects',choices: ['mss-eureka','mss-oauth','mss-gateway','mss-monitor','mss-upms'], description: '选择需要构建的项目')
        choice(name: 'environment', choices: ['dev', 'pro'], description: '选择需要构建的环境')
    }
    stages {
        stage('Modifed profile') {
            steps {
                echo 'Modifed profile..'
                sh "python /root/dyScript/nsinformation/profile_result.py ${params.projects}/src/main/resources/application.yml \
                /opt/project_cfg/nsinformation/nsinformation.json ${params.projects} ${params.environment}"
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn --version'
                sh "mvn clean package -pl ${params.projects} -am -DskipTests=true"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            
            steps {
                echo 'stop docker'
                sh "ansible ${params.environment}_nsinformation -m command -a \
	'/usr/local/bin/docker-compose -f /opt/data/nsinformation/${params.environment}_env/spring-boot/${params.projects}/docker-compose.yml down'"
                echo 'rsync code'
                script{
                if(params.environment == 'dev'){
                   def deploy_ip='10.217.20.104'
                   sh "rsync -vzcur --progress --delete --exclude=inspectionImage --exclude=lszwImage \
	${params.projects}/target/ dyzhswbest01@${deploy_ip}::${params.projects} --password-file=/etc/rsync.password"
                }else{
                    def deploy_ip='10.217.20.104'
                    sh "rsync -vzcur --progress --delete --exclude=inspectionImage --exclude=lszwImage \
	${params.projects}/target/ dyzhswbest01@${deploy_ip}::${params.projects} --password-file=/etc/rsync.password"
                }
                
                echo 'start docker'
                }
                sh "ansible ${params.environment}_nsinformation -m command -a \
	'/usr/local/bin/docker-compose -f /opt/data/nsinformation/${params.environment}_env/spring-boot/${params.projects}/docker-compose.yml up -d'"
            }
        }
    }
    post{
        always{
            dingTalk accessToken: 'https://oapi.dingtalk.com/robot/send?access_token=6980e30f268d00950e68420e4bcf72a81829acef5f384354d40e6ee5da3ffcd0', imageUrl: '', jenkinsUrl: '', message: '农水信息化项目发布', notifyPeople: '15187709007'
        }
    }
}