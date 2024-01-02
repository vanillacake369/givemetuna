package com.sparta.givemetuna.domain.checklist.repository;

import static com.sparta.givemetuna.domain.card.entity.QCard.card;
import static com.sparta.givemetuna.domain.checklist.entity.QChecklist.checklist;
import static com.sparta.givemetuna.domain.user.entity.QUser.user;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.global.config.QueryDslConfig;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChecklistCustomRepositoryImpl implements ChecklistCustomRepository {

	private final QueryDslConfig qd;

	@Override
	public Optional<Checklist> findFirstByAssigneeAndCardId(long assignee, long cardId) {
		return Optional.of(
			qd.query()
				.selectFrom(checklist)
				.where(checklist.user.id.eq(assignee), checklist.card.id.eq(cardId))
				.leftJoin(checklist.user, user)
				.leftJoin(checklist.card, card)
				.fetchFirst());
	}

}
