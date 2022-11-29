def buildJar() {
     echo 'building Jar file...'
     sh 'mvn install:install-file -DgroupId=javax.jms -DartifactId=jms -Dversion=1.1 -Dpackaging=jar -Dfile=jms-1.1.jar'
     sh 'mvn install:install-file -DgroupId="javax.jms" -DartifactId="jms" -Dversion="1.1" -Dpackaging="jar" -Dfile="jms-1.1.jar"'
     sh 'mvn package'
}

def buildImg() {
    echo 'building docker image ...'
    withCredentials([usernamePassword(credentialsID: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t dockerysf/my-app:3.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push dockerysf/my-app:3.0'
    }
}

def testApp() {
     echo 'testing app...'
}

def deployApp() {
     echo 'deploying app...'
     echo "deploying version ${params.VERSION}"
}

return this
