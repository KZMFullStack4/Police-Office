package com.moshka.dao;

import com.moshka.dto.TotalReportDto;
import com.moshka.dto.TotalReportResponse;
import com.moshka.enums.DossierStatus;
import com.moshka.enums.PoliceManStatus;
import com.moshka.model.DossierModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DossierRepository  extends GeneralRepositoryImpl<DossierModel,Long> {

    @Autowired
    private PoliceManRepository policeManRepository;
    public DossierRepository(){
        super(DossierModel.class);
    }

    public List<DossierModel>getAllOpenedOrClosedDossiers(boolean opened){
        Criteria criteria = getCurrentSession().createCriteria(DossierModel.class);
        if(opened){
            criteria.add(Restrictions.eq("dossierStatus", DossierStatus.OPENED));
            return criteria.list();
        }else{
            criteria.add(Restrictions.eq("dossierStatus", DossierStatus.CLOSED));
            return criteria.list();
        }
    }

    public Integer closeDossier(Long dossierId){
       int changed = super.getCurrentSession().createQuery("update DossierModel set dossierStatus=:value where id=:id ")
                .setParameter("value", DossierStatus.CLOSED)
                .setParameter("id",dossierId).executeUpdate();
        DossierModel model =getCurrentSession().get(DossierModel.class,dossierId);
        if(model.getPoliceManId()!=null){
            Long policeManId =model.getPoliceManId().getId();
            policeManRepository.changePoliceManStatus(policeManId, PoliceManStatus.FREE);
        }
        //By this line , when a dossier is closed the police of that dossier should become free in order to look into other dossiers
        return changed;
    }

    public Integer allocateDossiers(Long dossierId,Long policeManId){
       getCurrentSession().createQuery("update DossierModel set policeManId.id=:policeMan where id=:id ")
                .setParameter("policeMan" ,policeManId)
                .setParameter("id",dossierId).executeUpdate();
       return  getCurrentSession().createQuery(" update PoliceManModel set policeManStatus=:busyStatus where id=:id")
                .setParameter("busyStatus",PoliceManStatus.BUSY)
                .setParameter("id",policeManId).executeUpdate();
    }

    public List<TotalReportResponse>getTotalReport(TotalReportDto reportDto){
        StringBuilder query = new StringBuilder(" ");
        query.append("select creation_date,\n" +
                "creation_time,\n" +
                "tbl_dossier.description,\n" +
                "dossier_status,\n" +
                "plaintiff_id,\n" +
                "tbl_police_man.name as police_man_name,\n" +
                "tbl_police_man.family as police_family,\n" +
                "fathers_name as police_man_father_name,\n" +
                "personnel_code,\n" +
                "police_man_status,\n" +
                "tbl_plaintiff.name as plaintiff_name,\n" +
                "tbl_plaintiff.family as plaintiff_family,\n" +
                "tbl_plaintiff.national_code\n" +
                "\n" +
                "from bicycle.tbl_dossier\n" +
                "full outer join bicycle.tbl_police_man on tbl_dossier.policemanid_id=tbl_police_man.id\n" +
                "left join bicycle.tbl_plaintiff on bicycle.tbl_dossier.plaintiff_id=bicycle.tbl_plaintiff.id where 1=1 ");

       if(reportDto.getIsClosedFlag()!=0){
           query.append(" and tbl_dossier.dossier_status=:dossierStatus ");
       }

       if(reportDto.getIsBusyFlag()!=0){
           query.append( " and  tbl_police_man.police_man_status=:policeStatus");
       }
        NativeQuery finalQuery=getCurrentSession().createNativeQuery(query.toString());

        if(reportDto.getIsClosedFlag()!=0){
            switch (reportDto.getIsClosedFlag()){
                case 1:
                    finalQuery.setParameter("dossierStatus",DossierStatus.OPENED.toString());
                    break;
                case 2:
                    finalQuery.setParameter("dossierStatus",DossierStatus.CLOSED.toString());
                    break;
            }
        }
        if(reportDto.getIsBusyFlag()!=0){
            switch (reportDto.getIsBusyFlag()){
                case 1:
                    finalQuery.setParameter("policeStatus",PoliceManStatus.FREE.toString());
                    break;
                case 2:
                    finalQuery.setParameter("policeStatus",PoliceManStatus.BUSY.toString());
                    break;
            }
        }

        List<Object[]>queryResultArray = finalQuery.getResultList();

        List<TotalReportResponse>finalResponseList=new ArrayList<>();

        queryResultArray.stream().map((mapValue)->{
            TotalReportResponse response = new TotalReportResponse();
            response.setCreationDate(mapValue[0] == null ? "" : mapValue[0].toString());
            response.setCreationTime(mapValue[1] == null ? "" : mapValue[1].toString());
            response.setDescription(mapValue[2] == null ? "" : mapValue[2].toString());
            response.setDossierStatus(mapValue[3] == null ? "" : mapValue[3].toString());
            response.setPlaintiffId(mapValue[4] == null ? 0 : Integer.parseInt(mapValue[4].toString()));
            response.setPoliceName(mapValue[5] == null ? "" : mapValue[5].toString());
            response.setPoliceFamily( mapValue[6] == null ? "" : mapValue[6].toString());
            response.setPoliceFathersName(mapValue[7] == null ? "" : mapValue[7].toString());
            response.setPolicePersonnelCode(mapValue[8] == null ? "" : mapValue[8].toString());
            response.setPoliceManStatus(mapValue[9] == null ? "" : mapValue[9].toString());
            response.setPlaintiffName(mapValue[10] == null ? "" : mapValue[10].toString());
            response.setPlaintiffFamily(mapValue[11] == null ? "" : mapValue[11].toString());
            response.setPlaintiffNationalCode(mapValue[12] == null ? "" : mapValue[12].toString());
            return response;
        }).forEach(finalResponseList::add);

        return finalResponseList;
    }
}
