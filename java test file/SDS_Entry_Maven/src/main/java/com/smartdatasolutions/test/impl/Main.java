package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.*;
import java.util.*;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter() {
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter() {
		return new MemberImporterImpl();
	}

	@Override
	protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
		Set<Member> uniqueMembers = new HashSet<>(membersFromFile);
		return new ArrayList<>(uniqueMembers);
	}

	@Override
	protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
		Map<String, List<Member>> stateMembersMap = new HashMap<>();
		for (Member member : validMembers) {
			String state = member.getState();
			stateMembersMap.computeIfAbsent(state, k -> new ArrayList<>()).add(member);
		}
		return stateMembersMap;
	}

	public static void main(String[] args) {
		Main main = new Main();
		File inputMemberFile = new File("C:\\Users\\Shivam Singh\\Downloads\\java test file\\SDS_Entry_Maven\\Members.txt");
		String outputDirectory = "C:\\Users\\Shivam Singh\\Downloads\\java test file\\SDS_Entry_Maven\\output";
		String outputFileName = "output.txt"; 
		try {
			if (inputMemberFile.exists()) {
				main.convert(inputMemberFile, outputDirectory, outputFileName);
				System.out.println("Conversion completed successfully.");
			} else {
				System.err.println("Error: Input file not found.");
			}
		} catch (Exception e) {
			System.err.println("Error occurred during conversion: " + e.getMessage());
		}
	}
}
