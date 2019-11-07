/*
 * Copyright (c) 2019 Acmedcare+
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.acmedcare.framework.cpcdp.gson;

import com.acmedcare.framework.cpcdp.annotation.JsonKey;
import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * {@link CpcdpFieldNamingStrategy}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/6.
 */
public class CpcdpFieldNamingStrategy implements FieldNamingStrategy {
  /**
   * Translates the field name into its JSON field name representation.
   *
   * @param f the field object that we are translating
   * @return the translated field name.
   * @since 1.3
   */
  @Override
  public String translateName(Field f) {
    if (f.isAnnotationPresent(JsonKey.class)) {
      JsonKey key = f.getAnnotation(JsonKey.class);
      if (key != null && key.value().trim().length() > 0) {
        return key.value().toUpperCase(Locale.ENGLISH);
      }
    }
    return separateCamelCase(f.getName(), "_").toUpperCase(Locale.ENGLISH);
  }

  /**
   * Converts the field name that uses camel-case define word separation into separate words that
   * are separated by the provided {@code separatorString}.
   */
  private static String separateCamelCase(String name, String separator) {
    StringBuilder translation = new StringBuilder();
    for (int i = 0, length = name.length(); i < length; i++) {
      char character = name.charAt(i);
      if (Character.isUpperCase(character) && translation.length() != 0) {
        translation.append(separator);
      }
      translation.append(character);
    }
    return translation.toString();
  }

  /** Ensures the JSON field names begins with an upper case letter. */
  static String upperCaseFirstLetter(String name) {
    StringBuilder fieldNameBuilder = new StringBuilder();
    int index = 0;
    char firstCharacter = name.charAt(index);
    int length = name.length();

    while (index < length - 1) {
      if (Character.isLetter(firstCharacter)) {
        break;
      }

      fieldNameBuilder.append(firstCharacter);
      firstCharacter = name.charAt(++index);
    }

    if (!Character.isUpperCase(firstCharacter)) {
      String modifiedTarget = modifyString(Character.toUpperCase(firstCharacter), name, ++index);
      return fieldNameBuilder.append(modifiedTarget).toString();
    } else {
      return name;
    }
  }

  private static String modifyString(char firstCharacter, String srcString, int indexOfSubstring) {
    return (indexOfSubstring < srcString.length())
        ? firstCharacter + srcString.substring(indexOfSubstring)
        : String.valueOf(firstCharacter);
  }
}
