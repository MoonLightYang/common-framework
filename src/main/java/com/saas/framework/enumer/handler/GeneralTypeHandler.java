package com.saas.framework.enumer.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.saas.framework.enumer.IEnum;

public final class GeneralTypeHandler<E extends IEnum> extends BaseTypeHandler<E> {

	private Class<E> type;

	private E[] enums;

	public GeneralTypeHandler() {

	}

	public GeneralTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		this.enums = this.type.getEnumConstants();
		if (this.enums == null) {
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		if (jdbcType == null) {
			ps.setObject(i, parameter.getValue());
		} else {
			ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE);
		}

	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Integer code = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		}

		return getEnmByCode(code);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer code = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		}

		return getEnmByCode(code);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer code = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		}

		return getEnmByCode(code);
	}

	private E getEnmByCode(Object code) {
		if (code == null)
			throw new NullPointerException("the result code is null " + code);
		if (code instanceof Integer) {
			for (E e : enums) {
				if (e.getValue() == code) {
					return e;
				}
			}
			throw new IllegalArgumentException("Unknown enumtype, please check the value :  " + code);
		}

		if (code instanceof String) {
			for (E e : enums) {
				if (code.equals(e.getValue())) {
					return e;
				}
			}
			throw new IllegalArgumentException("Unknown enumtype, please check the value :  " + code);
		}

		throw new IllegalArgumentException("Unknown enumtype, please check the value :  " + code);
	}
}