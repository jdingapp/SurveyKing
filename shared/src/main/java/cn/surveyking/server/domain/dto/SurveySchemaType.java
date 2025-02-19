package cn.surveyking.server.domain.dto;

import lombok.Data;

import java.util.EnumSet;
import java.util.List;

/**
 * @author javahuang
 * @date 2021/8/10
 */
@Data
public class SurveySchemaType implements Cloneable {

	private String id;

	private String title;

	private String description;

	private QuestionType type;

	private Attribute attribute;

	private List<DataSource> dataSource;

	private List<SurveySchemaType> children;

	private List<Row> row;

	public enum QuestionType {

		FillBlank, MultipleBlank, Single, Multi, Select, Cascader, Upload, MatrixAuto, MatrixSingle, MatrixMulti, MatrixFillBlank, MatrixScore, Survey, QuestionSet, Pagination, Remark, SplitLine, Option;

		// 分为数据类型和空类型
		public static EnumSet<QuestionType> dataType() {
			return EnumSet.of(FillBlank, MultipleBlank, Single, Multi, Select, Cascader, Upload, MatrixAuto,
					MatrixSingle, MatrixMulti, MatrixFillBlank, MatrixScore);
		}

		public static EnumSet<QuestionType> voidType() {
			return EnumSet.of(Survey, QuestionSet, Pagination, Remark, SplitLine, Option);
		}

	}

	@Data
	public static class Row {

		private String id;

		private String title;

	}

	@Data
	public static class Attribute {

		private Boolean hidden;

		private Integer width;

		private String dataType;

		private Boolean required;

		private Boolean defaultChecked;

		private Integer rows;

		/**
		 * 范围强制校验
		 */
		private String scope;

		/**
		 * 范围强校验提示信息
		 */
		private String scopeDesc;

		/**
		 * 范围软校验，超出范围会警告，但是依然可以提交
		 */
		private String softScope;

		private String softScopeDesc;

		private Boolean readOnly;

		private String suffix;

		/**
		 * 文字长度限制 [1,2] [,5]
		 */
		private String textLimit;

		private Boolean finish;

		private String finishRule;

		private String calculate;

		private Integer currentPage;

		private Integer totalPage;

		private String submitButton;

		private String visibleRule;

		private Integer numericScale;

		/**
		 * 必填校验规则
		 */
		private String requiredRule;

		/**
		 * 背景图
		 */
		private String backgroundImage;

		/**
		 * 问卷头背景图
		 */
		private String headerImage;

		/**
		 * 上传文件类型后缀，多个文件格式逗号分割
		 */
		private String fileAccept;

		/**
		 * 最大上传文件数量
		 */
		private Integer maxFileCount;

		/**
		 * 单个上传文件大小限制
		 */
		private Double maxFileSize;

	}

	@Data
	public static class DataSource {

		private String label;

		private String value;

		private List<DataSource> children;

	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return new SurveySchemaType();
		}
	}

}
