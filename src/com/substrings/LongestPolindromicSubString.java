package com.substrings;

public class LongestPolindromicSubString {
	
	static class Result
	{
		int start,length;
	}

	public String lps(String s)
	{
		String res = null;
		
		Result result = new Result();
		if(s ==null || s.length()<=1)
			return s;
		
		for(int i=0; i<s.length()-1;i++)
		{
			//even length
			maxSubString(s, i,i,result);
			//odd length
			maxSubString(s, i,i+1, result);
		}
		
		return s.substring(result.start, result.start + result.length);
	}

	private void maxSubString(String s, int l, int r,Result result) {
		
		while(l>=0 && r<s.length()&&s.charAt(l) == s.charAt(r))
		{
			l--;r++;
		}
		
		if(result.length < r-l-1)
		{
			result.length = r-l-1;
			result.start = l+1;
		}
	}
	
	public static void main(String[] args) {
		LongestPolindromicSubString l = new LongestPolindromicSubString();
		System.out.println(l.lps("abad"));
	}
}
