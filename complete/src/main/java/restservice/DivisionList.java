package restservice;

public class DivisionList {

    static String[] divisionList = {
            "ARG (809)","Asheville (501)","Athens (201)","Atlanta (202)","Baton Rouge (613)","Birmingham (401)","Blytheville (614)","Bradenton / Sarasota (114)","Brookhaven (610)","Cape Coral (109C)","Charleston (303)","Cherokee C&D Landfill","Citrus County (122)","Clean Pro (820) ","Clearwater (121)","Cocoa (102)","Columbus (606)","Commercial (109D)","Concord (502)","Crestview (113)","Daytona Beach (112)","Dunedin (121a)","East Point (110C)","Fanning Springs (105)","Ft. Myers (109)","Ft. Pierce (103)","Gainesville / Alachua (104)","Gautier (601)","Greenwood (609)","Gulfport (601B)","Hattiesburg (603)","Hilton Head (301)","Houma (612)","Jackson (605)","Jacksonville (107)","Jonesboro (611)","Lake City (111)","Lake County (123)","Memphis (607)","Meridian (602)","Mobile (402)","N Fort Myers (109B)","Natchez (608)","New Orleans (604)","Ocala (120)","Orlando (100)","Palm Coast / St. Augustine (106)","Panama City (118)","Pembroke Pines (117)","Pending Division Assignment","Pensacola (116)","Pompano Beach (119)","Professional Waste (822)","Putnam (108)","S Fort Myers (109A)","Sanford (101)","South Charlotte (503)","St. Augustine Transfer Station (801)","Support (109E)","Tallahassee (110)","Wakulla County (110B)","Waste Pro USA (900)","West Palm Beach (115)"
    };



    public static Boolean doesDivisionExist(String division) {

        for (int i = 0; i < divisionList.length; i++) {
            if (divisionList[i].equals(division))
                return true;
        }

        return false;
    }

}
