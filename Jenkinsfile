pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Compile project'
                sh "chmod +x gradlew"
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                echo 'Run Unit Tests'
                sh './gradlew test'
            }
        }
    }
}