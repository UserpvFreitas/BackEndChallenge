package com.challenge.backend.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.challenge.backend.model.Video;
import com.challenge.backend.repository.VideoRepository;

public class UpdateVideoForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Length(min = 5)
	private String descricao;
	@NotNull @NotEmpty @Length(min = 5)
	private String url;
	
	private Long category;

	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category == null ? 1 : category;
	}

	public Video atualizar(Long id, VideoRepository videoRepository) {
		Video video = videoRepository.getReferenceById(id);
		video.setTitle(this.titulo);
		video.setDescription(this.descricao);
		video.setUrl(this.url);
		
		return video;
	}

}
