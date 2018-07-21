
/* DBManager.java */

package javaiina;

import java.util.*;

public class DBManager {
    
    /* replace to Derby later */
    private class DB {
        private List<AvailableSize> mAvailableSizeList;
        private List<Member> mMemberList;
        private List<Rental> mRentalList;
        private List<RentalObject> mRentalObjectList;
        private List<RentalObjectSizeInfo> mRentalObjectSizeInfoList;
        
        public DB() {
            this.mAvailableSizeList = new ArrayList<>();
            this.mMemberList = new ArrayList<>();
            this.mRentalList = new ArrayList<>();
            this.mRentalObjectList = new ArrayList<>();
            this.mRentalObjectSizeInfoList = new ArrayList<>();
        }
        
        public boolean isResistoredMember(Member member) {
            for (int i = 0; i < this.mMemberList.size(); ++i) {
                if (this.mMemberList.get(i).id() == member.id()) return true;
            }
            return false;
        }
    }
    
    public DBManager() {
        return;
    
    }
}
