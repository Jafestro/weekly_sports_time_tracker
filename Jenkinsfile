pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'f2173128-83c0-4591-be81-5972b42f1982'
        DOCKERHUB_REPO = 'jafestro/sports_tracker'
        DOCKER_IMAGE_TAG = 'ver1'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Jafestro/weekly_sports_time_tracker.git'
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean test' // For Linux agents
                    } else {
                        bat 'mvn clean test' // For Windows agents
                    }
                }
            }
        }
        stage('Code Coverage') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn jacoco:report'
                    } else {
                        bat 'mvn jacoco:report'
                    }
                }
            }
        }
        stage('Publish Test Results') {
            steps {
                // Publish JUnit test results
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                // Publish Jacoco coverage report
                jacoco()
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}")
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}