/*-
 *   Copyright (C) 2021 Presidenza del Consiglio dei Ministri.
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
package it.interop.dgc.gateway.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import it.interop.dgc.gateway.entity.SignerInformationEntity;

@Repository
public class SignerInformationRepository {

	@Autowired
	private MongoTemplate mongoTemplate;


	public SignerInformationEntity save(SignerInformationEntity signerInformationEntity) {
		return mongoTemplate.save(signerInformationEntity);
	}

	public SignerInformationEntity getByKid(String kid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("kid").is(kid));
		return mongoTemplate.findOne(query, SignerInformationEntity.class);
	}

	public int setAllTrustedPartyRevoked(String revokedBatchTag) {
		int numDoc = 0;
		List<SignerInformationEntity> trustedPartyList = mongoTemplate.findAll(SignerInformationEntity.class);
		if (trustedPartyList!=null) {
			numDoc = trustedPartyList.size();
			for (SignerInformationEntity trustedParty:trustedPartyList) {
				if (!trustedParty.isRevoked()) {
					trustedParty.setRevoked(true);
					trustedParty.setRevokedDate(new Date());
					trustedParty.setRevokedBatchTag(revokedBatchTag);
					mongoTemplate.save(trustedParty);
				}
			}
		}
		return numDoc;
	}
	
	public Long maxIndex() {
		Query query = new Query().with(Sort.by("id").descending()).limit(1);
		SignerInformationEntity ret = mongoTemplate.findOne(query, SignerInformationEntity.class);
        return (ret == null || ret.getIndex() == null) ? 0 : ret.getIndex();
    }
	
	
}