/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007 The Sakai Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.assignment.impl.conversion.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.assignment.impl.conversion.api.SchemaConversionHandler;

public class CombineDuplicateSubmissionsConversionHandler implements SchemaConversionHandler 
{
	private static final Log log = LogFactory.getLog(CombineDuplicateSubmissionsConversionHandler.class);

	public boolean convertSource(String id, Object source, PreparedStatement updateRecord) throws SQLException 
	{
		List<String> xml = (List<String>) source;
		SortedSet<String> identifiers = new TreeSet<String>();

		List<AssignmentSubmissionAccess> saxlist = new ArrayList<AssignmentSubmissionAccess>();
		for(int i = 0; i < xml.size(); i++)
		{
			AssignmentSubmissionAccess sax = new AssignmentSubmissionAccess();
			saxlist.add(sax);
			try
			{
				sax.parse(xml.get(i));
				identifiers.add(sax.getId());
			}
			catch (Exception e1)
			{
				log.warn("Failed to parse " + id + "[" + xml + "]", e1);
				// return false;
			}
		}
		
		for(int i = saxlist.size() - 1; i > 0; i--)
		{
			saxlist.set(i - 1, combineItems(saxlist.get(i), saxlist.get(i - 1)));
		}
		
		AssignmentSubmissionAccess result = saxlist.get(0);
		
		String xml0 = result.toXml();
		String id0 = result.getId();
		
		log.info("updating \"" + id0 + " (revising XML as follows:\n" + xml0);
		
		updateRecord.setString(1, xml0);
		updateRecord.setString(2, id0);
		
		return true;
	}

	protected AssignmentSubmissionAccess combineItems(AssignmentSubmissionAccess item1, AssignmentSubmissionAccess item2) 
	{
		AssignmentSubmissionAccess instructor;
		AssignmentSubmissionAccess student;
		
		//it is student-generated	(submitted==TRUE && dateSubmittted==SOME_TIMESTAMP) or submitted=false),
		//or it is instructor generated	(submitted==TRUE && dateSubmitted==null)
		if("true".equals(item1.getSubmitted()) && item1.getDatesubmitted() == null)
		{
			instructor = item1;
			student = item2;
		}
		else
		{
			instructor = item2;
			student = item1;
		}

		// need to verify whether student or instructor data 
		// takes precedence if both exist
		if(student.getDatereturned() == null)
		{
			student.setDatereturned(instructor.getDatereturned());
		}
		if(student.getDatesubmitted() == null)
		{
			student.setDatesubmitted(instructor.getDatesubmitted());
		}
		List feedbackattachments = student.getFeedbackattachments();
		if(feedbackattachments == null || feedbackattachments.isEmpty())
		{
			student.setFeedbackattachments(instructor.getFeedbackattachments());
		}
		if(student.getFeedbackcomment() == null)
		{
			student.setFeedbackcomment(instructor.getFeedbackcomment());
		}
		if(student.getFeedbackcomment_html() == null)
		{
			student.setFeedbackcomment_html(instructor.getFeedbackcomment_html());
		}
		if(student.getFeedbacktext() == null)
		{
			student.setFeedbacktext(student.getFeedbacktext());
		}
		if(student.getFeedbacktext_html() == null)
		{
			student.setFeedbacktext_html(instructor.getFeedbacktext_html());
		}
		if(student.getGrade() == null)
		{
			student.setGrade(instructor.getGrade());
		}
		if(student.getGraded() == null)
		{
			student.setGraded(instructor.getGraded());
		}
		if(student.getGradereleased() == null)
		{
			student.setGradereleased(instructor.getGradereleased());
		}
		if(student.getReturned() == null)
		{
			student.setReturned(instructor.getReturned());
		}
		if(student.getReviewReport() == null)
		{
			student.setReviewReport(instructor.getReviewReport());
		}
		if(student.getReviewScore() == null)
		{
			student.setReviewScore(instructor.getReviewScore());
		}
		if(student.getReviewStatus() == null)
		{
			student.setReviewStatus(instructor.getReviewStatus());
		}
		if(student.getScaled_grade() == null)
		{
			student.setScaled_grade(instructor.getScaled_grade());
		}
		// what to do with properties????
//		if(student.getSerializableProperties() == null)
//		{
//			student.setSerializableProperties(instructor.getSerializableProperties());
//		}
		
		return student;
	}

	public Object getSource(String id, ResultSet rs) throws SQLException 
	{
		List<String> xml = new ArrayList<String>();
		if(rs.first())
		{
			do
			{
				xml.add(rs.getString(1));
			}
			while(rs.next());
		}
		
		return xml;
	}

	public Object getValidateSource(String id, ResultSet rs)
			throws SQLException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void validate(String id, Object source, Object result)
			throws Exception 
	{
		
	}

}
