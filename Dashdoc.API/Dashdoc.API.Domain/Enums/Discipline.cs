using System.ComponentModel;

namespace Dashdoc.API.Domain.Enums;

public enum Discipline
{
    [Description("Speech Therapy")]
    SpeechTherapy,
    [Description("Occupational Therapy")]
    OccupationalTherapy,
    [Description("Physical Therapy")]
    PhysicalTherapy,
    [Description("Counseling")]
    Counseling,
    [Description("Psychology")]
    Psychology,
    [Description("TBD")]
    Hes,
    [Description("TBD")]
    Vt,
    [Description("TBD")]
    At,
    [Description("TBD")]
    Setss
}