#namespace("mobile")
    #sql("list")
        select * from mobile limit #para(0)
    #end
    #sql("find_by_city")
        select * from mobile where city = #para(name) or city_code = #para(code)
    #end
#end
