package com.sparta.givemetuna.domain.checklist.entity;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.checklist.dto.ChecklistCreateRequestDto;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "checklist")
@NoArgsConstructor
@AllArgsConstructor
public class Checklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String contents;

	@Column
	private Boolean check;

	@Column
	private Integer priority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "card_id")
	private Card card;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee")
	private User user;

	public Checklist(String contents, boolean b, int i, Card card, User user) {
		this.contents = contents;
		this.check = b;
		this.priority = i;
		this.card = card;
		this.user = user;
	}

	public static Checklist of(ChecklistCreateRequestDto checklistCreateRequestDto, boolean b, int i, Card card, User user) {
		return new Checklist(
			checklistCreateRequestDto.getContents(),
			b,
			i,
			card,
			user
		);
	}
}
