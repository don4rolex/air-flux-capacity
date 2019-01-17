package com.andrew.utility;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateSerializer extends JsonSerializer<Date> {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
      .withZone(ZoneId.systemDefault());

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeString(formatter.format(value.toInstant()));
  }
}