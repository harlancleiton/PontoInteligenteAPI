package br.harlan.api.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.harlan.api.enums.TypeReleaseEnum;

@Document
public class PointReleases {

	@Id
	private String id;
	private Date releaseDate;
	private String description;
	private String location;
	private Date creationDate;
	private Date updateDate;
	private TypeReleaseEnum typeReleaseEnum;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public TypeReleaseEnum getTypeReleaseEnum() {
		return typeReleaseEnum;
	}
	public void setTypeReleaseEnum(TypeReleaseEnum typeReleaseEnum) {
		this.typeReleaseEnum = typeReleaseEnum;
	}
}