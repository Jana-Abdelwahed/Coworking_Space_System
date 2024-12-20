public class TeachingRoom extends Room {
    private String projectorType;
    private String boardType;
    private String InstructorName;

    public TeachingRoom(String name, int ID, String projectorType, String boardType) {
        super(name, ID);
        this.projectorType = projectorType;
        this.boardType = boardType;
    }

    public String getProjectorType() {
        return projectorType;
    }

    public String getBoardType() {
        return boardType;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String instructorName) {

        InstructorName = instructorName;
    }


}
