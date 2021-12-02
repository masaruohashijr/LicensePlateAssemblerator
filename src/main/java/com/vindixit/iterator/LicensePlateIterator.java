package com.vindixit.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class LicensePlateIterator implements Iterable<LicensePlate> {

	private LicensePlate _next;
	private Object[] _licensePlates;
	private LicensePlateAssemblerator _licenseAssemblerator;

	public LicensePlateIterator(Iterator<String> _licensePlatesIterator) {
		Object[] ar = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(_licensePlatesIterator, 0), false).toArray();
		this._licensePlates = ar;
		this._licenseAssemblerator = new LicensePlateAssemblerator(ar);
	}

	public boolean hasNext() {
		return this._licenseAssemblerator.hasNext();
	}

	public LicensePlate next() {
		return this._licenseAssemblerator.next();
	}

	@Override
	public Iterator<LicensePlate> iterator() {
		LicensePlateAssemblerator iterator = new LicensePlateAssemblerator(this._licensePlates);
		return iterator;
	}

	private class LicensePlateAssemblerator implements Iterator {

		int cursor;
		private Object[] _licensePlates;

		public LicensePlateAssemblerator(Object[] ar) {
			this._licensePlates = ar;
			this.cursor = 0;
		}

		public boolean hasNext() {
			if(cursor < _licensePlates.length) {
				try {
					Object object = this._licensePlates[cursor];
					cursor++;
					LicensePlate licensePlate = new LicensePlate(object.toString());
					cursor--;
					return true;
				} catch (Exception e) {
					return hasNext();
				}
			}
			return false;
		}

		public LicensePlate next() {
			LicensePlate licensePlate = null;
			if (cursor == this._licensePlates.length) {
				try {
					Object object = this._licensePlates[cursor - 1];
					licensePlate = new LicensePlate(object.toString());
					cursor++;
					return licensePlate;
				} catch (Exception e) {
					return null;
				}
			}
			if(!hasNext()) {
				return null;
			}
			try {
				Object object = this._licensePlates[cursor - 1];
				licensePlate = new LicensePlate(object.toString());
				return licensePlate;
			} catch (Exception e) {
				cursor++;
				licensePlate = next();
			}
			return licensePlate;
		}
	}

}
