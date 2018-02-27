package com.imooc.miaosha.validator;
import  javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.imooc.miaosha.util.ValidatorUtil;
/**
 * 自定义validation
 * @author U6035457
 * Defines the logic to validate a given constraint A for a given object type T. 
 *ConstraintValidator定义了两个泛型参数, 第一个是这个校验器所服务到标注类型,对哪个接口负责, 
 *第二个这个校验器所支持到被校验元素到类型 (即String).
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

	private boolean required = false;
	
	public void initialize(IsMobile constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {
			return ValidatorUtil.isMobile(value);
		}else {
			if(StringUtils.isEmpty(value)) {
				return true;
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}

}
