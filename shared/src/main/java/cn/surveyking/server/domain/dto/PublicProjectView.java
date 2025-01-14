package cn.surveyking.server.domain.dto;

import lombok.Data;

/**
 * 答卷页面
 *
 * @author javahuang
 * @date 2021/8/26
 */
@Data
public class PublicProjectView {

	private SurveySchemaType survey;

	private Integer status;

	private ProjectSetting.AnswerSetting setting;

	private String name;

	private Boolean passwordRequired;

}
