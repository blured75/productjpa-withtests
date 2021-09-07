node {
  def mvnHome
  stage('Preparation') { // for display purposes
    // Get the Maven tool.
    // ** NOTE: This 'M3' Maven tool must be configured
    // **       in the global configuration.
    mvnHome = tool name: 'maven-3', type: 'maven'
  }
  stage('Eureka Server') {
      stage('Git CO - Eureka Server') {
          // Get some code from a GitHub repository
          git 'https://github.com/blured75/eureka-server.git'
      }
      stage('Build - Eureka Server') {
        // Run the maven build
        sh "${mvnHome}/bin/mvn clean package"
      }
      stage('Results - Eureka Server') {
        archiveArtifacts 'target/*.jar'
      }
  }
  stage('Product API') {
        stage('Git CO - Product API') {
            // Get some code from a GitHub repository
            git 'https://github.com/blured75/productjpa-withtests.git'
        }
        stage('Build - Product API') {
          // Run the maven build
          sh "${mvnHome}/bin/mvn clean package"
        }
        stage('Results - Product API') {
          archiveArtifacts 'target/*.jar'
        }
    }
}
