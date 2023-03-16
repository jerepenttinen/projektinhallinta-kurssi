import ProfileContacts from "../components/ProfileContacts"
import ProfileRecipeList from "../components/ProfileRecipeList"
import HorizontalRecipeList from "../components/HorizontalRecipeList"
import ReviewList from "../components/ReviewList"

const dummyData: { id: number, header: string }[] = [
        { id:1, header: "Kinkkukiusaus" },
        { id:2, header: "Lihamureke" },
        { id:3, header: "Makaronilaatikko" },
        { id:4, header: "Hernekeitto" },
        { id:5, header: "Jauhelihakastike" },
        { id:7, header: "Jauhelihakastike2" },
        { id:8, header: "Jauhelihakastike3" },
        { id:9, header: "Jauhelihakastike4" },
]
const dummyReviewData: {id:number, name: string, comment: string, date: Date, }[]=[
    {id:1, name: "Hernekeitto", comment:"Suorastaan herkullista!", date:new Date(2017, 4, 4)},
    {id:2, name: "Makaronilaatikko", comment:"Suorastaan oksettavaa!", date:new Date(2022, 2, 7)}
]
const ProfilePage = () => {

    return(
        <div className="m-auto bg-white p-4" style={{width:"50%", minWidth:300}}>
            <ProfileContacts/>
            <h2 className="mt-4">Reseptit</h2>
            <ProfileRecipeList/>
            <h2 className="mt-4">Kokoelmat</h2>
            <HorizontalRecipeList imageSize={2} fontSize={10} header={"Aamupalat"} data={dummyData}/>
            <br/>
            <ReviewList reviews={dummyReviewData}/>
        </div>
    );
}

export default ProfilePage;