package com.uader.poo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Anotación para ignorar propiedades JSON desconocidas
import com.fasterxml.jackson.annotation.JsonProperty;  // Anotación para mapear nombres de propiedades JSON
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true) // Anotación: Si el JSON de entrada tiene propiedades que no están
                                            // definidas en esta clase Java, simplemente las ignora en lugar de lanzar un error.
                                            // Útil si la información de Git viene con más campos de los que te interesan.
@Getter
@Setter
public class GitInfoDTO { 

	@JsonProperty("git.branch") // Anotación: Mapea la propiedad JSON "git.branch" a este campo Java 'branch'.
                                // Esto es necesario porque los nombres de las propiedades de Git (ej. "git.branch")
                                // no son nombres de variables Java válidos.
	String branch;

	@JsonProperty("git.build.host")
	String buildHost; 
	@JsonProperty("git.build.time")
	String buildTime; 
	@JsonProperty("git.build.user.email") 
	String buildUserEmail;

	@JsonProperty("git.build.user.name") 
	String buildUsername;

	@JsonProperty("git.build.version")
	String buildVersion;

	@JsonProperty("git.closest.tag.commit.count") 
	String closestTagCommitCount;

	@JsonProperty("git.closest.tag.name") 
	String closestTagName;

	@JsonProperty("git.commit.id") 
	String commitId;

	@JsonProperty("git.commit.id.abbrev") 
	String commitIdAbbrev;

	@JsonProperty("git.commit.id.describe") 
	String commitIdDescribe;

	@JsonProperty("git.commit.id.describe-short") 
	String commitIdDescribeShort;

	@JsonProperty("git.commit.message.full") 
	String commitMessageFull;

	@JsonProperty("git.commit.message.short") 
	String commitMessageShort;

	@JsonProperty("git.commit.time") 
	String commitTime;

	@JsonProperty("git.commit.user.email") 
	String commitUserEmail;

	@JsonProperty("git.commit.user.name") 
	String commitUsername;

	@JsonProperty("git.dirty") 
	String dirty;

	@JsonProperty("git.remote.origin.url") 
	String remoteOriginUrl;

	@JsonProperty("git.tags") 
	String tags;
}
