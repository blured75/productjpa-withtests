node {
  def mvnHome
  stage('Preparation') { // for display purposes
    // Get some code from a GitHub repository
    git 'https://github.com/blured75/eureka-server.git'
    // Get the Maven tool.
    // ** NOTE: This 'M3' Maven tool must be configured
    // **       in the global configuration.
    mvnHome = tool 'M3'
  }
  stage('Eureka Server') {
      stage('Build - Eureka Server') {
        // Run the maven build
        sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      }
      stage('Results - Eureka Server') {
        archiveArtifacts 'target/*.jar'
      }
      stage('Docker - Eureka Server') {
        docker.build("cloudnativejava/eureka-server")
      }
  }
}
