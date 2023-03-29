package org.openmrs.module.icare.laboratory.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.module.icare.core.utils.ChildIdOnlyDeserializer;
import org.openmrs.module.icare.core.utils.ChildIdOnlySerializer;
import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "lb_test_time_configuration")
public class TestTimeConfig extends BaseOpenmrsData implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "config_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "concept_id")
	@JsonSerialize(using = ChildIdOnlySerializer.class)
	@JsonDeserialize(using = ChildIdOnlyDeserializer.class)
	private Concept concept;
	
	@Column(name = "version")
	private String version;
	
	@Column(name = "standard_tat")
	private Integer standardTAT;
	
	@Column(name = "urgent_tat")
	private Integer urgentTAT;
	
	@Column(name = "additional_request_time_limit")
	private Integer addReqTimeLimit;
	
	public TestTimeConfig() {
		
	}
	
	public void setStandardTAT(Integer stdTAT) {
		this.standardTAT = stdTAT;
	}
	
	public Integer getStandardTAT() {
		return standardTAT;
	}
	
	public void setUrgentTAT(Integer urgTAT) {
		this.urgentTAT = urgTAT;
	}
	
	public Integer getUrgentTAT() {
		return urgentTAT;
	}
	
	public void setAddReqTimeLimit(Integer addReqTimeLimit) {
		this.addReqTimeLimit = addReqTimeLimit;
	}
	
	public Integer getAddReqTimeLimit() {
		return addReqTimeLimit;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static TestTimeConfig fromMap(Map<String, Object> testTimeConfigMap) {
		
		//		samplemap
		//		{
		//			concept: uuid,
		//			standardTAT: 182681,
		//			urgentTAT: 912739,
		//			additionalReqTimeLimit: 6328763
		//
		//		}
		
		TestTimeConfig testTimeConfig = new TestTimeConfig();
		
		testTimeConfig.setAddReqTimeLimit((Integer) testTimeConfigMap.get("additionalReqTimeLimit"));
		testTimeConfig.setStandardTAT((Integer) testTimeConfigMap.get("standardTAT"));
		testTimeConfig.setUrgentTAT((Integer) testTimeConfigMap.get("urgentTAT"));
		testTimeConfig.setVersion((String) testTimeConfigMap.get("version"));
		
		if (testTimeConfigMap.get("uuid") == null) {
			
		} else {
			testTimeConfig.setUuid((String) testTimeConfigMap.get("uuid"));
		}
		
		Concept testConcept = new Concept();
		testConcept.setUuid((String) testTimeConfigMap.get("concept"));
		
		testTimeConfig.setConcept(testConcept);
		
		return testTimeConfig;
	}
	
	public Map<String, Object> toMap() {
		
		HashMap<String, Object> testTimeConfigMap = new HashMap<String, Object>();
		
		HashMap<String, Object> conceptMap = new HashMap<String, Object>();
		
		conceptMap.put("display", this.getConcept().getDisplayString());
		conceptMap.put("uuid", this.getConcept().getUuid());
		conceptMap.put("name", this.getConcept().getName().getName());
		
		testTimeConfigMap.put("concept", conceptMap);
		
		testTimeConfigMap.put("uuid", this.getUuid());
		
		testTimeConfigMap.put("standardTAT", this.getStandardTAT());
		
		testTimeConfigMap.put("urgentTAT", this.getUrgentTAT());
		
		testTimeConfigMap.put("version", this.getVersion());
		
		testTimeConfigMap.put("additionalReqTimeLimit", this.getAddReqTimeLimit());
		Map<String, Object> creatorObject = new HashMap<String, Object>();
		
		if (this.getCreator() != null) {
			creatorObject.put("uuid", this.getCreator().getUuid());
			creatorObject.put("display", this.getCreator().getDisplayString());
			creatorObject.put("name", this.getCreator().getName());
		}
		
		testTimeConfigMap.put("creator", creatorObject);
		
		testTimeConfigMap.put("created", this.getDateCreated());
		
		Map<String, Object> updatorObject = new HashMap<String, Object>();
		
		if (this.getChangedBy() != null) {
			updatorObject.put("uuid", this.getChangedBy().getUuid());
			updatorObject.put("display", this.getChangedBy().getDisplayString());
			updatorObject.put("display", this.getChangedBy().getName());
			
		}
		
		testTimeConfigMap.put("lastUpdatedBy", updatorObject);
		
		testTimeConfigMap.put("lastUpdated", this.getDateChanged());
		
		return testTimeConfigMap;
	}
	
	public Concept getConcept() {
		return concept;
	}
	
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
}
