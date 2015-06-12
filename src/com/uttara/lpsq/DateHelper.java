package com.uttara.lpsq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateHelper 
{
	public static String DateChecker(String date)
	{		
		//String compDate=date.substring(8)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String presentDate = format.format(new Date()).toString();
		Date d1 = null;
		Date d2 = null;
		try {
				d1 = format.parse(date);
				d2 = format.parse(presentDate);
				//in milliseconds
				
				long diff = d2.getTime() - d1.getTime();
						
				long diffDays = diff / (24 * 60 * 60 * 1000);
				System.out.print(diffDays + " Days");
				
			
				int day=(int)diffDays;
				if(day<0)
				{
					return " Date is accepted upto this date only, Future date is not allowed to add as Birthdate <br>";
				}
				else
				{
					return ""+Constants.SUCCESS;
				}	
				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();System.out.println(e.getMessage());
			return " Dear user Kindly provide Date in this format : <h5>FORMAT : DD/MM/YYYY</h5>";
		}				
	}
	
	
	/*public static Date javaToHsqlDateConverter(String date) throws ParseException, java.text.ParseException
	{
		String alter=""+date.substring(6)+"-"+date.substring(3, 5)+"-"+date.substring(0,2);
		System.out.println("Alter date is "+ alter);
		Date d1=new Date();
		java.sql.Date d2=null;
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
			d1=df.parse(alter);
			d2=new java.sql.Date(d1.getTime());
			System.out.println(d1.toString());
			return d2;		
	}
	*/
	public static Date createhsqlDate()
	{
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date d= new java.sql.Date(new java.util.Date().getTime());
		
		return d;
	}
	
	
	public static String HsqlDateToUtilDateConverter(Date date) 
	{
					
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date d=null;
		try{
			System.out.println("   "+date);
			d = new Date(date.getTime());
			return format.format(d).toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("inside of DateHelper of HsqlDateToUtilDateConverter "+e.getMessage());
		}
		return null;
				
	}
	
	
	
	public static ArrayList<String> birthdaycalculator(String date)
	{
		System.out.println(date);

		int altermon=Integer.parseInt(date.substring(3, 5));
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String presentDate = format.format(new Date()).toString();
		Date d1 = null;
		Date d2 = null;
		try {
				ArrayList<String> returnDays= new ArrayList<String>();
				d1 = format.parse(date);
				d2 = format.parse(presentDate);
				
				
				System.out.println("Formated date of Contact "+d1.toString());
				System.out.println("Formated date of Contact "+d2.toString());
				
				c1.setTime(d1);
				int conmonth=c1.get(Calendar.MONTH);
				conmonth+=1;
				System.out.println("Con month is "+conmonth);
				
				c2.setTime(d2);
				int premonth=c2.get(Calendar.MONTH);
				premonth+=1;
				System.out.println("present month is "+premonth);
				
				if(altermon==premonth)
				{
					System.out.println("inside of same month");
					String[] between= betweenweek();
					System.out.println("Between days [ From ="+between[0]+"  to  "+between[1]+"Max no of days are "+between[2]);
					
					if(between[2].equals("31"))
					{
						ArrayList<String> days=get31DaysList();
						int flag=0;
						int count=0;
						one:for(String s:days)
						{
							if(between[2].equals("31"))
							{
								returnDays.add(s);
								flag=1;
								count++;
							}
							if(flag==1)
							{
								if(returnDays.contains(s)==false)
								{
									returnDays.add(s);
									count++;
									if(count==8)
									break one;
								}								
							}
						}
						flag=0;
						count=0;
					return returnDays;
					
						
					}else if(between[2].equals("30"))
					{
						System.out.println("Date of 30 days  ");
						ArrayList<String> days=get30DaysList();
						int flag=0;
						int count=0;
						one:for(String s:days)
						{
							//System.out.println(s +" = "+ between[0] );
							if(s.contains(between[0]))
							{
								returnDays.add(s);
								flag=1;
								count++;
							}
							if(flag==1)
							{
								if(returnDays.contains(s)==false)
								{
									returnDays.add(s);
									count++;
									if(count==8)
									break one;
								}	
							}
						}
						//System.out.println(count);
						flag=0;
						count=0;
						return returnDays;
						
					}else if(between[2].equals("29"))
					{
						ArrayList<String> days=get29DaysList(d2);
						int flag=0;
						int count=0;
						one:for(String s:days)
						{
							if(s.contains(between[0]))
							{
								returnDays.add(s);
								flag=1;
								count++;
							}
							if(flag==1)
							{
								if(returnDays.contains(s)==false)
								{
									returnDays.add(s);
									count++;
									if(count==8)
									break one;
								}	
							}
						}
						flag=0;
						count=0;
						return returnDays;
						
					}else if(between[2].equals("28"))
					{
						ArrayList<String> days=get28DaysList();
						int flag=0;
						int count=0;
						one:for(String s:days)
						{
							if(s.contains(between[0]))
							{
								returnDays.add(s);
								flag=1;
								count++;
							}
							if(flag==1)
							{
								if(returnDays.contains(s)==false)
								{
									returnDays.add(s);
									count++;
									if(count==8)
									break one;
								}	
							}
						}
						flag=0;
						count=0;
						return returnDays;
					}
				}			
				return returnDays;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Some error occured "+e.getMessage());
		}
		return null;
		
		
	}
	
	
	public static ArrayList<String> get31DaysList()
	{
		Calendar c1=Calendar.getInstance();
		Date d1=new Date();
		c1.setTime(d1);
		int conmonth=c1.get(Calendar.MONTH);
		conmonth+=1;
		
		ArrayList<String> day31= new ArrayList<String>();
		one:for(int i=1;i<39;i++)
		{
			if(i<32)
			{
				if(i<10)
				day31.add("0"+i+"/"+conmonth);
				else
				day31.add(i+"/"+conmonth);	
			}else
			{
				conmonth+=1;
				for(int j=1;j<8;j++)
				{
					if(conmonth==13)
					{
						day31.add(j+"/"+1);
					}else
						day31.add(j+"/"+conmonth);	
				}	
				break;
			}				
		}
		System.out.println(day31+" Size "+day31.size());
		return day31;
	}
	
	
	public static ArrayList<String> get30DaysList()
	{
		Calendar c1=Calendar.getInstance();
		Date d11=new Date();
		c1.setTime(d11);
		int conmonth=c1.get(Calendar.MONTH);
		conmonth+=1;

		ArrayList<String> day30= new ArrayList<String>();
		one:for(int i=1;i<38;i++)
		{
			if(i<31)
			{
					if(i<10)
					day30.add("0"+i+"/"+conmonth);
					else
					day30.add(i+"/"+conmonth);
			}else
			{
				conmonth+=1;
				for(int j=1;j<8;j++)
				{
					if(conmonth==13)
					{
						day30.add(j+"/"+1);
					}else
						day30.add(j+"/"+conmonth);	
				}	
				break;
			}				
		}
		System.out.println(day30+" Size "+day30.size());
		return day30;
		
	}
	
	
	public static ArrayList<String> get28DaysList()
	{
		Calendar c1=Calendar.getInstance();
		Date d11=new Date();
		c1.setTime(d11);
		int conmonth=c1.get(Calendar.MONTH);
		conmonth+=1;

		ArrayList<String> day28= new ArrayList<String>();
		one:for(int i=1;i<38;i++)
		{
			if(i<29)
			{
				if(i<10)
					day28.add("0"+i+"/"+conmonth);
					else
					day28.add(i+"/"+conmonth);
			}else
			{
				conmonth+=1;
				for(int j=1;j<8;j++)
				{
					if(conmonth==13)
					{
						day28.add(j+"/"+1);
					}else
						day28.add(j+"/"+conmonth);	
				}	
				break;
			}				
		}
		System.out.println(day28+" Size "+day28.size());
		return day28;
		
	}
	
	public static ArrayList<String> get29DaysList(Date d1)
	{
		Calendar c1=Calendar.getInstance();
		Date d11=new Date();
		c1.setTime(d11);
		int conmonth=c1.get(Calendar.MONTH);
		conmonth+=1;

		ArrayList<String> day29= new ArrayList<String>();
		one:for(int i=1;i<38;i++)
		{
			if(i<30)
			{
				if(i<10)
					day29.add("0"+i+"/"+conmonth);
					else
					day29.add(i+"/"+conmonth);
			}else
			{
				conmonth+=1;
				for(int j=1;j<8;j++)
				{
					if(conmonth==13)
					{
						day29.add(j+"/"+1);
					}else
						day29.add(j+"/"+conmonth);	
				}	
				break;
			}				
		}
		System.out.println(day29+" Size "+day29.size());
		return day29;
		
	}
	
	
	
	public static String[] betweenweek()
	{
		String[] betdate= new String[3];
		
		Calendar c1= Calendar.getInstance();
		 c1.add(Calendar.DAY_OF_MONTH, 0);
		 
		 Calendar c2= Calendar.getInstance();
		 c2.add(Calendar.DAY_OF_MONTH, 7);
		 
		 SimpleDateFormat d= new SimpleDateFormat("dd/MM");
		 Date d1=c1.getTime();
		 betdate[0]=d.format(d1).toString();
		 
		 Date d2=c2.getTime();
		 betdate[1]=d.format(d2).toString();
		 betdate[2]=""+c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		 return betdate;
	}
	
}
