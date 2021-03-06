import React from 'react'
import ContentSection from './ContentSection'

const TeamMember = ({ photoId, name, description }) => (
    <div className="col-xl-4 col-lg-4 col-md-5 col-sm-6 col-12 justify-content-center">
        <img
            src={'img/portraits/' + photoId + '.png'}
            className="col-xl-10 col-lg-10 col-md-10 col-sm-8 col-5 mb-2 mt-2"
            alt={name}
        />
        <div className="col-12">
            <h4>{name}</h4>
            <div className="team-member-description">{description}</div>
        </div>
    </div>
)

const TeamSection = () => (
    <ContentSection name="team" title="Core Team">
        <div className="row justify-content-center">
            <TeamMember photoId="aiosup" name="Prof. dr. ir. Alexandru Iosup" description="Project Lead" />
            <TeamMember
                photoId="gandreadis"
                name="Georgios Andreadis"
                description="Software Engineer responsible for the frontend web application"
            />
            <TeamMember
                photoId="fmastenbroek"
                name="Fabian Mastenbroek"
                description="Software Engineer responsible for the datacenter simulator"
            />
            <TeamMember
                photoId="jburley"
                name="Jacob Burley"
                description="Software Engineer responsible for prefabricated components"
            />
            <TeamMember
                photoId="loverweel"
                name="Leon Overweel"
                description="Former product lead and Software Engineer"
            />
        </div>
        <div className="text-center lead mt-3">
            See{' '}
            <a target="_blank" href="http://atlarge.science/opendc#team" rel="noopener noreferrer">
                atlarge.science/opendc
            </a>{' '}
            for the full team!
        </div>
    </ContentSection>
)

export default TeamSection
