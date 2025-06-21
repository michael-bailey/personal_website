plugins {

}

tasks.register<Exec>("installFrontend") {
	workingDir = file(".")
	commandLine("sh", "-c", "npm install")
}

tasks.register<Exec>("runFrontendDevelopment") {
	workingDir = file(".")
	dependsOn(":frontend:installFrontend")
	commandLine("sh", "-c", "npm run dev")
}

tasks.register<Exec>("buildFrontend") {
	workingDir = file(".")
	dependsOn(":frontend:installFrontend")
	commandLine("sh", "-c", "npm", "build")
}