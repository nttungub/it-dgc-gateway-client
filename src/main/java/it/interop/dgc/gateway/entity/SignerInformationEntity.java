/*-
 *   Copyright (C) 2021 Ministero della Salute and all other contributors.
 *   Please refer to the AUTHORS file for more information. 
 *   This program is free software: you can redistribute it and/or modify 
 *   it under the terms of the GNU Affero General Public License as 
 *   published by the Free Software Foundation, either version 3 of the
 *   License, or (at your option) any later version.
 *   This program is distributed in the hope that it will be useful, 
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 *   GNU Affero General Public License for more details.
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program. If not, see <https://www.gnu.org/licenses/>.   
 */
package it.interop.dgc.gateway.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import it.interop.dgc.gateway.enums.CertificateType;
import lombok.Data;

@Data
@Document(collection = "signer_information")
public class SignerInformationEntity implements Serializable {
	private static final long serialVersionUID = 5989282342501802070L;

	@Id
    private String id;

    @Field(name="id")
    private Long resumeToken;
    
    @Field(name="kid")
    private String kid;
    
    @Field(name = "country")
    private String country;

    @Field(name = "thumbprint")
    private String thumbprint;

    @Field(name = "raw_data")
    private String rawData;

    @Field(name = "signature")
    private String signature;

    @Field(name = "certificate_type")
    private CertificateType certificateType;
    
    @CreatedDate
    @Field(name="created_at")
    private Date createdAt;

	@Field("revoked")
	private boolean revoked;

	@Field("revoked_date")
	private Date revokedDate;

    @Field(name="batch_tag")
    private String downloadBatchTag;
    
    @Field(name="batch_tag_revoke")
    private String revokedBatchTag;

}
