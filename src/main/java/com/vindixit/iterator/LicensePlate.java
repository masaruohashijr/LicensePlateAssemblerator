package com.vindixit.iterator;

public class LicensePlate {
	
	private StateCode _code;
	private String _plateNumber;
	
	public enum StateCode {
		AR,TX,NE,CA,OH
	}
	
	public LicensePlate(String plate) {
		_code = StateCode.valueOf(plate.substring(0, 2));
		_plateNumber = plate.substring(3);
	}

	public StateCode get_code() {
		return _code;
	}

	public void set_code(StateCode _code) {
		this._code = _code;
	}

	public String get_plateNumber() {
		return _plateNumber;
	}

	public void set_plateNumber(String _plateNumber) {
		this._plateNumber = _plateNumber;
	}
	
}
