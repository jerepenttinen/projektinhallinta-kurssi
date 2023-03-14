import ProfileContacts from "../components/ProfileContacts"
import ProfileRecipeList from "../components/ProfileRecipeList"
const ProfilePage = () => {

    return(
        <div className="m-auto bg-white p-4" style={{width:"50%", minWidth:300}}>
            <ProfileContacts/>
            <h2 className="mt-4">Reseptit</h2>
            <ProfileRecipeList/>

        </div>
    );
}

export default ProfilePage;