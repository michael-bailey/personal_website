package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.JavaExec
import task.LoadEnvironmentFileTask

class EnvironmentFilePlugin : Plugin<Project> {
	override fun apply(project: Project) {


		val extension = project.extensions.create(
			"envFile",
			EnvironmentFilePluginExtension::class.java
		)

		val loadEnvVars = project.tasks.register(
			"LoadEnvFile",
			LoadEnvironmentFileTask::class.java
		) {
			it.includeRoot = extension.includeRootEnvFile
		}

		project.tasks.named("bootRun", JavaExec::class.java) { task ->
			task.dependsOn(loadEnvVars)

			task.doFirst {
				val envVars: Map<String, String> = loadEnvVars.get().envMap
				envVars.entries.forEach {
					task.environment[it.key] = it.value
				}
			}
		}
	}
}