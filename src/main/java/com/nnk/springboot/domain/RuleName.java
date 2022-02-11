package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 
 * implementation of business object: Rule Name that will be manipulated by the
 * other layers.
 *
 * the Rule Name has seven attributes : id, name, description, json, template,
 * sqlStr and sqlPart
 */
@Entity
@Table(name = "rulename")
public class RuleName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 4)
	private Integer id;
	@NotBlank(message = "Name is mandatory!")
	@Column(length = 125)
	private String name;
	@NotBlank(message = "Description is mandatory!")
	@Column(length = 125)
	private String description;
	@NotBlank(message = "Json is mandatory!")
	@Column(length = 125)
	private String json;
	@NotBlank(message = "Template is mandatory!")
	@Column(length = 512)
	private String template;
	@NotBlank(message = "Sql Str Rating is mandatory!")
	@Column(name = "sqlStr", length = 125)
	private String sqlStr;
	@NotBlank(message = "Sql Part is mandatory!")
	@Column(name = "sqlPart", length = 125)
	private String sqlPart;

	/**
	 * Instantiates a new rule name.
	 *
	 * @param name        : the name
	 * @param description : the description
	 * @param json        : the json
	 * @param template    : the template
	 * @param sqlStr      : the sql str
	 * @param sqlPart     : the sql part
	 */

	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		super();
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	@Override
	public String toString() {
		return "RuleName [id=" + id + ", name=" + name + ", description=" + description + ", json=" + json
				+ ", template=" + template + ", sqlStr=" + sqlStr + ", sqlPart=" + sqlPart + "]";
	}

	public RuleName() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}

}
