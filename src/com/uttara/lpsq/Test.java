package com.uttara.lpsq;

import java.lang.reflect.Method;

public class Test 
{
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException 
	{
		String s1="JaiHindu";
		char[] arr=new char[s1.length()];
		for(int i=0;i<arr.length/2;i++)
		{
			arr[i]=s1.charAt(s1.length()-(1+i));
			arr[arr.length-(i+1)]=s1.charAt(i);
		}
		StringBuilder builder= new StringBuilder();
		for(char c:arr)
			builder.append(c);
		System.out.println(builder.toString());
		System.out.println();
		Class class1=Class.forName("com.uttara.lpsq.StudentBean");
		
		Method [] methods=class1.getMethods();
		ClassLoader class2=class1.getClassLoader();
		
		for(Method method1:methods)
		{
			System.out.println("Starting ...");
			System.out.println(method1.getName());
			System.out.println(method1.getReturnType().getPackage());
			Class[]ar2=method1.getParameterTypes();
			for(Class class3:ar2)
			{
				System.out.println();
				System.out.println("Starting of Parameter types");
				System.out.println(class3.getName());				
				System.out.println(class3.getConstructor(class3).getName());
				
				System.out.println("Ending of Parameter type");
				System.out.println();
			}
			System.out.println("Ending ...");
			System.out.println();
		}
		
	}
}
