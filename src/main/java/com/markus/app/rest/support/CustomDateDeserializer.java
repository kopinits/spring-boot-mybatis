package com.markus.app.rest.support;

import java.io.IOException;
import java.util.Date;

import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext ctx)
			throws IOException, JsonProcessingException {
		JsonToken token = parser.getCurrentToken();
		if (token == JsonToken.VALUE_STRING){
			String str = parser.getText().trim();
			return ISODateTimeFormat.dateTimeParser().parseDateTime(str).toDate();
		}
		if (token == JsonToken.VALUE_NUMBER_INT){
			return new Date(parser.getLongValue());
		}
		throw ctx.mappingException("Can not deserialize instance of DateTime");
	}

}
