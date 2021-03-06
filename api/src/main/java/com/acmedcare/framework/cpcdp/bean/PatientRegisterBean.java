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

package com.acmedcare.framework.cpcdp.bean;

import com.acmedcare.framework.cpcdp.Status;
import com.acmedcare.framework.cpcdp.annotation.Condition;
import com.acmedcare.framework.cpcdp.annotation.Conditions;
import com.acmedcare.framework.cpcdp.annotation.JsonKey;
import com.acmedcare.framework.cpcdp.annotation.Required;
import com.acmedcare.framework.cpcdp.consts.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * {@link PatientRegisterBean}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2019/11/6.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class PatientRegisterBean implements Serializable {

  private String id;

  /** 注册编号 */
  private String registerId;

  /** 医院 ID */
  @Required private String hospitalId;

  /** 姓名 */
  @Required private String name;

  /** 性别 */
  @Required private Gender gender;

  /**
   * Range: 0-250
   *
   * <p>
   */
  @Min(0)
  @Max(255)
  @Required
  private int age;

  /**
   * 出生日期
   *
   * <p>Example: 1999-12-01
   */
  private String birthday;

  /** 民族 */
  private String nation;

  /** 联系电话 */
  private String contactPhone;

  /** 证件类型 */
  @Required private CredentialsType credentialsType;

  /** 证件号 */
  @Required
  @Conditions({
    @Condition(
        field = "credentialsType",
        type = CredentialsType.class,
        isCpcEnum = true,
        expectValue = {"1", "2"})
  })
  private String idCard;

  /** 职业 */
  private Job job;

  /** 文化程度 */
  private Culturedegree culturedegree;

  /** 婚姻状况 */
  @JsonKey("MARITALSTATUS")
  private MaritalStatus maritalStatus;

  @Min(0)
  @Max(300)
  private int height;

  /** 体重 */
  @Min(0)
  @Max(300)
  private float weight;

  /**
   * 状态
   *
   * @see Status#S01
   * @see Status#S02
   */
  private Status status;

  // ====== Inner Beans Defined =======

}
