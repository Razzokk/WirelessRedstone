import net.darkhax.curseforgegradle.Constants
import net.darkhax.curseforgegradle.TaskPublishCurseForge

plugins {
	id("com.modrinth.minotaur")
	id("net.darkhax.curseforgegradle")
}

val common = project(":common")
evaluationDependsOn(common.path)

val javaVersion: Int by rootProject
val mcVersion: String by project
val modId: String by project
val modVersion: String by project
val clothConfigVersion: String by project
val jeiVersion: String by project
val neoVersion: String by project
val parchmentMappings: String by project
val curseforgeProjectId: String by project
val changelogProvider: Provider<String> by project

val modReleaseType: String by project
val modDisplayName = "[NeoForge $mcVersion] $modId-$modVersion"

base {
	archivesName.set("$modId-neoforge")
}

repositories {
	maven("https://maven.shedaniel.me/")	// Cloth config
	maven("https://maven.blamejared.com/")	// JEI
	maven("https://maven.neoforged.net/releases")
}

dependencies {
	implementation(project(common.path, configuration = common.configurations.namedElements.name))
	implementation(common.sourceSets["client"].output)

	neoForge("net.neoforged", "neoforge", neoVersion)

	modApi("me.shedaniel.cloth:cloth-config-forge:$clothConfigVersion")

	modLocalRuntime("mezz.jei:jei-$mcVersion-neoforge:$jeiVersion")
}

loom {
	runs {
		configureEach {
			ideConfigGenerated(true)
		}
	}

	mods {
		register(modId) {
			sourceSet(sourceSets.main.get())
		}
	}
}

tasks {
	// needed for the run configs
	processResources {
		from(common.sourceSets.main.get().resources)
	}

	withType<JavaCompile> {
		source(common.sourceSets.main.get().allJava)
		source(common.sourceSets["client"].allJava)
	}

	sourcesJar {
		from(common.sourceSets.main.get().allSource)
		from(common.sourceSets["client"].allSource)
	}
}

// Publishing

modrinth {
	if (project.hasProperty("debug")) debugMode.set(true)
	token.set(System.getenv("MODRINTH_TOKEN"))

	projectId.set(modId)
	versionNumber.set("neoforge-$modVersion")
	versionName.set(modDisplayName)
	versionType.set(modReleaseType)
	uploadFile.set(tasks.remapJar)
	changelog.set(changelogProvider)

	dependencies {
		optional.project("cloth-config")
	}
}

tasks.register<TaskPublishCurseForge>("curseforge") {
	if (project.hasProperty("debug")) debugMode = true
	apiToken = System.getenv("CURSEFORGE_TOKEN")

	val file = upload(curseforgeProjectId, tasks.remapJar)
	file.displayName = modDisplayName
	file.releaseType = modReleaseType
	file.changelog = changelogProvider.get()
	file.changelogType = Constants.CHANGELOG_MARKDOWN
	file.addJavaVersion("Java $javaVersion")
	file.addOptional("cloth-config")
}
