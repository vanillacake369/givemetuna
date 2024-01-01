package com.sparta.givemetuna.domain.issuecomment.repository.helper;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.sparta.givemetuna.domain.common.helper.QueryDslUtil;
import com.sparta.givemetuna.domain.issuecomment.entity.QIssueComment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

public final class IssueCommentQueryOrderFactory {


	/**
	 * 페이징 인스턴스로부터 IssueComment 컬럼에 따라 정렬기준치 리스트를 생성,반환
	 *
	 * @param pageable 페이징 인스턴스
	 * @return 정렬기준치 리스트
	 * @author 임지훈
	 */
	static List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {

		List<OrderSpecifier> ORDERS = new ArrayList<>();

		if (!ObjectUtils.isEmpty(pageable.getSort())) {
			for (Sort.Order order : pageable.getSort()) {
				Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

				switch (order.getProperty()) {
					case "id" -> {
						OrderSpecifier<?> orderId = QueryDslUtil.getSortedColumn(direction, QIssueComment.issueComment, "id");
						ORDERS.add(orderId);
					}
					case "userId" -> {
						OrderSpecifier<?> orderUser = QueryDslUtil.getSortedColumn(direction, QIssueComment.issueComment.user, "id");
						ORDERS.add(orderUser);
					}
					default -> {
					}
				}
			}
		}

		return ORDERS;
	}

	/**
	 * 페이징 인스턴스로부터 IssueComment 컬럼에 따라 정렬기준치를 배열화하여 반환
	 *
	 * @param pageable 페이징 인스턴스
	 * @return 정렬기준치 배열
	 * @author 임지훈
	 */
	public static OrderSpecifier[] getAllOrderSpecifiersArr(Pageable pageable) {
		return IssueCommentQueryOrderFactory
			.getAllOrderSpecifiers(pageable)
			.toArray(OrderSpecifier[]::new);
	}
}
