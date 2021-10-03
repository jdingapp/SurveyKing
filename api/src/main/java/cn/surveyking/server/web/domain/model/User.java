package cn.surveyking.server.web.domain.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author javahuang
 * @date 2021/8/23
 */
@Data
@TableName(value = "t_user", autoResultMap = true)
@Accessors(chain = true)
public class User implements UserDetails, Serializable {

	private String id;

	private String fullName;

	private String avatar;

	private String gender;

	private String username;

	private String phone;

	private String password;

	private String salt;

	/**
	 * 0失活 1 激活
	 */
	private Integer status;

	@TableField(fill = FieldFill.INSERT, select = false)
	private Date createAt;

	@TableField(fill = FieldFill.INSERT, select = false)
	private String createBy;

	@TableField(fill = FieldFill.UPDATE, select = false)
	private Date updateAt;

	@TableField(fill = FieldFill.UPDATE, select = false)
	private String updateBy;

	@TableLogic
	private Integer deleted;

	@TableField(exist = false)
	private Set<Role> authorities = new HashSet<>();

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return this.status != null && this.status == 1;
	}

}