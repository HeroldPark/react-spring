1. frontend/package.json에서 작업 사항

    "proxy": "http://localhost:8989",
    "scripts": {
        "start": "react-scripts start",
        "build": "react-scripts build",
        "test": "react-scripts test",
        "eject": "react-scripts eject"
    },

2. backend/build.gradle에서 작업 사항
    - projectDir은 backend가 된다.
    - frontend가 있는 위치를 제대로 입력해야 한다.

def frontendDir = "$projectDir/../frontend"

sourceSets {
    main {
        resources { srcDirs = ["$projectDir/src/main/resources"]
        }
    }
}
processResources { dependsOn "copyReactBuildFiles" }
task installReact(type: Exec) {
    workingDir "$frontendDir"
    inputs.dir "$frontendDir"
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
        commandLine "npm.cmd", "audit", "fix"
        commandLine 'npm.cmd', 'install' }
    else {
        commandLine "npm", "audit", "fix" commandLine 'npm', 'install'
    }
}
task buildReact(type: Exec) {
    dependsOn "installReact"
    workingDir "$frontendDir"
    inputs.dir "$frontendDir"
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
        commandLine "npm.cmd", "run-script", "build"
    } else {
        commandLine "npm", "run-script", "build"
    }
}
task copyReactBuildFiles(type: Copy) {
    dependsOn "buildReact"
    from "$frontendDir/build"
    into "$projectDir/src/main/resources/static"
}
