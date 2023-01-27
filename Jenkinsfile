pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building application...'
                dir('DoApp'){
                    sh 'npm install'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                dir('DoApp'){
                    sh 'npm test'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Building Docker image...'
                sh "docker build -t youssefjeh/nodejsauto:latest ."
                echo 'Pushing Docker image to DockerHub...'
                withCredentials([usernamePassword(credentialsId:'dockerhub-rep', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    sh "docker tag youssefjeh/nodejsauto:latest dockerysf/autnodejs:latest"
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                    sh "docker push dockerysf/autnodejs:latest"
                }
            }
        }
    }
}
