package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberImporterImpl implements MemberImporter {

	@Override
	public List< Member > importMembers( File inputFile ) throws Exception {

		List<Member> members = new ArrayList<>();
			Set<String> memberIds = new HashSet<>();
			try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
				String line;
				while ((line = br.readLine()) != null) {
					String id = line.substring(0, 12).trim();
					String lastName = line.substring(12, 37).trim();
					String firstName = line.substring(37, 62).trim();
					String address = line.substring(62, 92).trim();
					String city = line.substring(92, 112).trim();
					String state = line.substring(112, 116).trim();
					String zip = line.substring(116).trim();
					Member member = new Member(id, lastName, firstName, address, city, state, zip);
					if (!memberIds.contains(id)) {
						members.add(member);
						memberIds.add(id);
					}
				}
			}
			return members;
		}

//We use a Set<String> to keep track of unique member IDs to ensure that duplicate member records
// are not added to the list.
//If a member record with the same ID is encountered, it is not added to the list,
// meeting the requirement of not accepting duplicate member records.
}
