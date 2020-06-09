package com.saas.framework.aspect.params;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnotationParams {

	private boolean isVerifyLogin = false;
	private boolean isVerifyAuth = false;

	public AnnotationParams(boolean isVerifyLogin, boolean isVerifyAuth) {
		this.isVerifyLogin = isVerifyLogin;
		this.isVerifyAuth = isVerifyAuth;
	}

}
