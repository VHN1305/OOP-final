package display;

import com.*;
import model.constant.constant;

import java.util.Scanner;

public class ChooseTest {
    public void menuTest() {
        Scanner sc = new Scanner(System.in);
        int luachonAPI = constant.luachonAPI;
        int luachonTest;

        if(luachonAPI == 1){
            System.out.println("API: Upload Status");
            uploadStatus UpLoadStaTus = new uploadStatus();
            UpLoadStaTus.uploadStatus();
        }
        else if (luachonAPI == 3){
            System.out.println("API: Login");

            login lgt = new login();
            lgt.Login1();
            lgt.Login2();
            lgt.Login3();
            lgt.Login4();
            lgt.Login5();
            lgt.Login6();
            lgt.Login7();

        }
        else if (luachonAPI == 4) {
            System.out.println("API: Sign Up");
            signup signup = new signup();
            signup.SignUp1();
            signup.SignUp2();
            signup.SignUp3();
            signup.SignUp4();
            signup.SignUp5();
            signup.SignUp6();
            signup.SignUp7();
            signup.SignUp8();
            signup.SignUp9();
            signup.SignUp10();
            signup.SignUp11();
            signup.SignUp12();
            signup.SignUp13();
            signup.SignUp14();
            signup.SignUp15();
            signup.SignUp16();
        }
        else if(luachonAPI == 5 ){
            System.out.println("API: Edit Account");
            edit edit = new edit();
            edit.edit();
        }
        else if(luachonAPI == 6 ){
            System.out.println("API: Logout");
            logout logout = new logout();
            logout.Logout1();
        }
        else if(luachonAPI == 7 ){
            System.out.println("API: Change PassWord");
            changePass changePass = new changePass();
            changePass.ChangePass1();
            changePass.ChangePass2();
        }
        else if(luachonAPI == 8 ){
            System.out.println("API: Info");
            Info info = new Info();
            info.Info1();
            info.Info2();
        }
        else if(luachonAPI == 9 ){
            System.out.println("API: Get List Auctions");
            GetListAuctions getListAuctions = new GetListAuctions();
            getListAuctions.Test1();
            getListAuctions.Test2();
            getListAuctions.Test3();
            getListAuctions.Test4();
            getListAuctions.Test5();
            getListAuctions.Test6();
            getListAuctions.Test7();
        }
        else if(luachonAPI == 10 ){
            System.out.println("API: Get Detail Auction");
            GetDetailsAuctions getDetailsAuctions = new GetDetailsAuctions();
            getDetailsAuctions.GetDetailsAuctions();
        }
        else if(luachonAPI == 11 ){
            System.out.println("API: Creat Auction");
            CreateAuction createAuction = new CreateAuction();
            createAuction.Test1();
            createAuction.Test2();
            createAuction.Test3();
            createAuction.Test4();
            createAuction.Test5();
            createAuction.Test6();
            createAuction.Test7();
            createAuction.Test8();
            createAuction.Test9();
            createAuction.Test10();
        }
        else if(luachonAPI == 12 ){
            System.out.println("API: Edit Auction");
            EditAuction editAuction = new EditAuction();
            editAuction.EditAuction1();
            editAuction.EditAuction2();
            editAuction.EditAuction3();
        }
        else if(luachonAPI == 13 ){
            System.out.println("API: Delete Auction");
            deleteAuction deleteAuction = new deleteAuction();
            deleteAuction.deleteAuction();
            deleteAuction.DeleteAuction1();
            deleteAuction.DeleteAuction2();
            deleteAuction.DeleteAuction3();
        }
        else if(luachonAPI == 14){
            System.out.println("API: Info Auction");
            InfoAuction infoAuction = new InfoAuction();
            infoAuction.InfoAuction();
            infoAuction.InfoAuction1();
        }
        else if(luachonAPI == 15){
            System.out.println("API: Creat Item");
            CreatItem creatItem = new CreatItem();
            creatItem.CreatItem1();
            creatItem.CreatItem2();
            creatItem.CreatItem3();
            creatItem.CreatItem4();
        }
        else if(luachonAPI == 16){
            System.out.println("API: Edit Item");
            EditItem editItem = new EditItem();
            editItem.EditItem1();
            editItem.EditItem2();
        }
        else if(luachonAPI == 17){
            System.out.println("API: Info Item");
            InfoItem infoItem = new InfoItem();
            infoItem.InfoItem1();
        }
        else if(luachonAPI == 18){
            System.out.println("API: Create Comment");
            CreateComments createComments = new CreateComments();
            createComments.CreateComment1();
            createComments.CreateComment2();
            createComments.CreateComment3();
        }
        else if(luachonAPI == 19){
            System.out.println("API: Delete Comment");
            DeleteComment deleteComment = new DeleteComment();
            deleteComment.DeleteComment1();
            deleteComment.DeleteComment2();
            deleteComment.DeleteComment3();
        }
        else if(luachonAPI == 20 ){
            System.out.println("API: GetListComment");
            GetListComments getListComments = new GetListComments();
            getListComments.GetListComment1();
            getListComments.GetListComment2();
            getListComments.GetListComment3();
            getListComments.GetListComment4();
        }
        else if(luachonAPI == 21){
            System.out.println("API: CreatBid");
            CreateBid createBid = new CreateBid();
            createBid.CreatBid1();
            createBid.CreatBid2();
            createBid.CreatBid3();
            createBid.CreatBid4();
        }
        else if(luachonAPI == 22){
            System.out.println("API: Get List Bid");
            GetListBid getListBid = new GetListBid();
            getListBid.GetlistBid1();
            getListBid.GetListBid2();
            getListBid.GetListBid3();
            getListBid.GetListBid4();
        }
        else if(luachonAPI == 23){
            System.out.println("API: Get List Category");
            getListCategories getListCategories = new getListCategories();
            getListCategories.getlistcategories();
        }
        else if(luachonAPI == 24){
            System.out.println("API: Get List Brand");
            GetListBrands getListBrands = new GetListBrands();
            getListBrands.GetListBrands();
        }
        else if(luachonAPI == 25 ){
            System.out.println("API: Accep MaxBid");
            AcceptMaxBid acceptMaxBid = new AcceptMaxBid();
            acceptMaxBid.AccepMaxBid1();
            acceptMaxBid.AccepMaxBid2();
            acceptMaxBid.AccepMaxBid3();
        }
        else if(luachonAPI == 26 ){
            System.out.println("API: Contact Us");
            ContactUs contactUs = new ContactUs();
            contactUs.ContactUs1();
            contactUs.ContactUs2();
            contactUs.ContactUs3();
            contactUs.ContactUs4();
        }
        else if(luachonAPI == 27){
            System.out.println("API: Like Account");
            LikeAuction likeAuction = new LikeAuction();
            likeAuction.LikeAuction1();
            likeAuction.LikeAuction2();
            likeAuction.LikeAuction3();

        }
        else if(luachonAPI == 28){
            System.out.println("API: Get List Like");
            getListLikes getListLikes = new getListLikes();
            getListLikes.GetListLike1();
            getListLikes.GetListLike2();
            getListLikes.GetListLike3();
            getListLikes.GetListLike4();
        }
        else if(luachonAPI == 29){
            System.out.println("API: Total Like Auction");
            TotalLikesOfAuction totalLikesOfAuctio = new TotalLikesOfAuction();
            totalLikesOfAuctio.TotalLikesOfAuction();
        }
        else if(luachonAPI == 30){
            System.out.println("API: Get News");
            GetNews getNews = new GetNews();
            getNews.GetNews();
        }
        else if(luachonAPI == 31){
            System.out.println("API: Read News");
            ReadNews readNews = new ReadNews();
            readNews.ReadNews();
        }
        else if(luachonAPI == 32){
            System.out.println("API: Get Notification");
            GetNotifications getNotifications = new GetNotifications();
            getNotifications.GetNotification1();
            getNotifications.GetNotification2();
            getNotifications.GetNotification3();
        }
        else if(luachonAPI == 33){
            System.out.println("API: Read Notification");
            readNotifications readNotifications = new readNotifications();
            readNotifications.ReadNotifications();
        }
        else if(luachonAPI == 34){
            System.out.println("API: Get Slider");
            GetSlider getSlider = new GetSlider();
            getSlider.getSlider();
        }
        else if(luachonAPI == 35){
            System.out.println("API: Search");
            Search search = new Search();
            search.Search1();
            search.Search2();
            search.Search3();
            search.Search4();
            search.Search5();
            search.Search6();
        }
        else if(luachonAPI == 36){
            System.out.println("API: DeleteNotification");
            DeleteNotification deleteNotification = new DeleteNotification();
            deleteNotification.DeleteNotification1();
            deleteNotification.DeleteNotification2();
        }
        else if(luachonAPI == 37){
            System.out.println("API: Get List Chat");
            GetListChat getListChat = new GetListChat();
            getListChat.GetListChat();
        }
        else if(luachonAPI == 38){
            System.out.println("API: Creat Chat");
            createChat createChat = new createChat();
            createChat.CreatChat1();
            createChat.CreatChat2();
        }
        else if(luachonAPI == 39){
            System.out.println("API: Create Message of Chat");
            CreatMessageOfChat creatMessageOfChat = new CreatMessageOfChat();
            creatMessageOfChat.CreatMessageOfChat();
        }
        else if(luachonAPI == 40){
            System.out.println("API: Get List Message of Chat");
            GetListMessageOfChat getListMessageOfChat = new GetListMessageOfChat();
            getListMessageOfChat.GetListMessageOfChat();
            getListMessageOfChat.GetListChat();
        }
        else if(luachonAPI == 41){
            System.out.println("API: Delivery");
            Delivery delivery = new Delivery();
            delivery.Delivery();
        }
        else if(luachonAPI == 42){
            System.out.println("API: Rate");
            Rate rate = new Rate();
            rate.Rate();
        }
        else if(luachonAPI == 43){
            System.out.println("API: Get List Rate");
            GetListRate getListRate = new GetListRate();
            getListRate.getListRate();
        }
        else if(luachonAPI == 44){
            System.out.println("API: Edit Rate");
            EditRate editRate = new EditRate();
            editRate.EditRate();
        }
        else{
            return ;
        }

    }
}
