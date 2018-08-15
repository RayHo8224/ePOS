properties([
    buildDiscarder(logRotator(artifactNumToKeepStr:'2')),

    parameters([
        string(
            name:'BRANCH',
            defaultValue:'master',
            description:'欲建置的branch',
            trim:true
        )
    ])
])

node{ timestamps{

   stage('Checkout'){
       checkout([
           $class: 'GitSCM', 
           branches: [[name: "${BRANCH}"]], 
           doGenerateSubmoduleConfigurations: false, 
           extensions: [], 
           submoduleCfg: [], 
           userRemoteConfigs: [
               [url: 'https://github.com/RayHo8224/ePOS.git']
            ]
        ])
   }
   stage('Build'){
    sh 'ssh root@ray_maven "cd /Docker_Share/jenkins_home/workspace/ePOS && mvn clean package"'
    archiveArtifacts artifacts: 'target/ePOS.war', onlyIfSuccessful: true
         

   }

   stage('Deploy'){
try{
    sh 'ssh root@epos_ap1 sh "/home/jboss-eap-7.1/bin/jboss-cli.sh --connect /server-group=main-server-group:stop-servers"'
    sh 'ssh root@epos_ap1 sh "/home/jboss-eap-7.1/bin/jboss-cli.sh --connect --command=\'undeploy ePOS.war --server-groups=main-server-group\'"'
    sh 'ssh root@epos_ap1 sh "/home/jboss-eap-7.1/bin/jboss-cli.sh --connect --command=\'deploy /Docker_Share/jenkins_home/workspace/ePOS/target/ePOS.war --server-groups=main-server-group\'"'
    sh 'ssh root@epos_ap1 sh "/home/jboss-eap-7.1/bin/jboss-cli.sh --connect /server-group=main-server-group:start-servers"'
}catch (Exception ex) {
    sh 'ssh root@epos_ap1 sh "/home/jboss-eap-7.1/bin/jboss-cli.sh --connect /server-group=main-server-group:stop-servers"'
    sh 'ssh root@epos_ap1 sh "/home/jboss-eap-7.1/bin/jboss-cli.sh --connect --command=\'deploy /Docker_Share/jenkins_home/workspace/ePOS/target/ePOS.war --server-groups=main-server-group\'"'
    sh 'ssh root@epos_ap1 sh "/home/jboss-eap-7.1/bin/jboss-cli.sh --connect /server-group=main-server-group:start-servers"'

}
   }

    

}}